package com.open.wintertodt

import com.google.common.eventbus.Subscribe
import com.open.wintertodt.branch.IsInside
import com.open.wintertodt.helpers.CommonMethods
import com.open.wintertodt.helpers.SystemMessageManager
import com.open.wintertodt.models.Configuration
import com.open.wintertodt.models.Status
import com.open.wintertodt.models.WintertodtLocation
import org.powbot.api.Notifications
import org.powbot.api.Random
import org.powbot.api.event.BreakEvent
import org.powbot.api.event.MessageEvent
import org.powbot.api.rt4.Equipment
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Varpbits
import org.powbot.api.rt4.walking.model.Skill
import org.powbot.api.script.OptionType
import org.powbot.api.script.ScriptCategory
import org.powbot.api.script.ScriptConfiguration
import org.powbot.api.script.ScriptManifest
import org.powbot.api.script.paint.Paint
import org.powbot.api.script.paint.PaintBuilder
import org.powbot.api.script.tree.TreeComponent
import org.powbot.api.script.tree.TreeScript
import org.powbot.mobile.script.ScriptManager
import org.powbot.mobile.service.ScriptUploader

@ScriptManifest(
    name = "Opentodt",
    description = "Does wintertodt.",
    version = "1.0.4",
    category = ScriptCategory.Firemaking,
    author = "PTY",
    markdownFileName = "Opentodt.md"
)
@ScriptConfiguration.List(
    [
        ScriptConfiguration(
            "Location", "Location you want to burn at", OptionType.STRING,
            "SOUTH_WEST", ["SOUTH_WEST", "SOUTH_EAST"]
        ),
        ScriptConfiguration(
            "Food", "Food you wish to eat if required", OptionType.STRING,
            "",
            [
                "",
                "Shrimps", "Cooked chicken", "Cooked meat", "Sardine", "Bread", "Herring",
                "Mackerel", "Choc-ice", "Trout", "Cod", "Pike", "Roast beast meat", "Pineapple punch",
                "Salmon", "Tuna", "Jug of wine", "Rainbow fish", "Stew", "Cake", "Meat pie",
                "Lobster", "Bass", "Plain pizza", "Swordfish", "Potato with butter", "Apple pie",
                "Chocolate cake", "Tangled toads' legs", "Potato with cheese", "Meat pizza",
                "Monkfish", "Anchovy pizza", "Cooked karambwan", "Curry", "Ugthanki kebab", "Mushroom potato",
                "Shark", "Sea turtle", "Pineapple pizza", "Summer pie", "Manta ray", "Tuna potato", "Dark crab",
                "Anglerfish", "Saradomin brew(3)", "Saradomin brew(4)",
            ]
        ),
        ScriptConfiguration(
            "MinFood", "Minimum amount of food allowed", OptionType.INTEGER, "2"
        ),
        ScriptConfiguration(
            "BankFood", "Amount of food to withdraw to at bank", OptionType.INTEGER, "7"
        ),
        ScriptConfiguration(
            "Logs only", "Logs only", OptionType.BOOLEAN
        ),
        ScriptConfiguration(
            "Idle at 500", "Idle at 500", OptionType.BOOLEAN
        ),
        ScriptConfiguration(
            "Open crates", "Open crates just before banking", OptionType.BOOLEAN
        ),
        ScriptConfiguration(
            "Upgrade gear", "Upgrades warm gear", OptionType.BOOLEAN
        ),
        ScriptConfiguration(
            "Snowfall safespot", "Uses the snowfall safespot for cutting/fletching", OptionType.BOOLEAN
        ),
    ]
)
class Script : TreeScript() {

    override val rootComponent: TreeComponent<*> by lazy {
        IsInside(this)
    }

    lateinit var configuration: Configuration
    lateinit var status: Status

    override fun onStart() {
        val food = getOption<String>("Food")
        val logsOnly = getOption<Boolean>("Logs only")
        val idleAt500 = getOption<Boolean>("Idle at 500")
        val location = WintertodtLocation.valueOf(getOption("Location"))
        val minimumFood = getOption<Int>("MinFood")
        val bankFood = getOption<Int>("BankFood")
        val snowfallSafespot = getOption<Boolean>("Snowfall safespot")
        val openCrates = getOption<Boolean>("Open crates")
        val upgradeGear = getOption<Boolean>("Upgrade gear")

        configuration = Configuration(
            food, bankFood, minimumFood, location, logsOnly, idleAt500, snowfallSafespot, openCrates, upgradeGear
        )
        status = Status(configuration.startingLocation)
        addPaint()
        checkSetupInventory(configuration.logsOnly, configuration.foodName)
    }

    private fun addPaint() {
        val p: Paint = PaintBuilder.newBuilder()
            .addString("Last leaf:") { lastLeaf.name }
            .trackSkill(Skill.Firemaking)
            .trackSkill(Skill.Woodcutting)
            .trackSkill(Skill.Fletching)
            .trackSkill(Skill.Construction)
            .trackInventoryItems(20703)
            .y(45)
            .x(40)
            .build()
        addPaint(p)
    }

    private fun checkSetupInventory(logsOnly: Boolean, foodName: String?) {
        val inventory = Inventory.get()
        val equipment = Equipment.get()

        if (!inventory.any { it.name() == "Tinderbox" || it.name() == "Bruma torch" } && !equipment.any { it.name() == "Bruma torch" }) {
            Notifications.showNotification("Please start with a Tinderbox or Bruma torch")
            ScriptManager.stop()
        } else if (!inventory.any { it.name().contains(" axe") } && !equipment.any { it.name().contains("axe") }) {
            Notifications.showNotification("Please start script with an axe in inventory or equipped")
            ScriptManager.stop()
        } else if (!inventory.any { it.name() == "Hammer" }) {
            Notifications.showNotification("Please start script with a hammer in your inventory")
            ScriptManager.stop()
        } else if (!logsOnly && !inventory.any { it.name() == "Knife" }) {
            Notifications.showNotification("Please start script with a knife in your inventory or disable fletching")
            ScriptManager.stop()
        } else if (foodName.isNullOrEmpty()) {
            Notifications.showNotification("Please select food or you ass will be dead.")
            ScriptManager.stop()
        }
    }

    /**
     *  Subscribes to the messages received from the game and updates the status accordingly
     *
     *  @param messageEvent The message received form the game.
     */
    @Subscribe
    fun message(messageEvent: MessageEvent) {
        SystemMessageManager.messageRecieved(messageEvent)
    }

    /**
     *  This will only let the script break when its not in a game currently
     */
    @Subscribe
    fun breakEvent(breakEvent: BreakEvent) {
        val gameRunning = Varpbits.varpbit(OpenWintertodtConstants.VARPBIT_RESPAWN) == 0
        if (gameRunning) {
            val remainingPercent = CommonMethods.remainingHealthPercentage()
            val breakTime = if (remainingPercent < 5) Random.nextInt(5000, 8000) else remainingPercent * 1.5
            breakEvent.delay(breakTime.toLong())
        } else {
            breakEvent.accept()
        }
    }
}

fun main(args: Array<String>) {
    ScriptUploader().uploadAndStart("Opentodt", "", "127.0.0.1:5625", true, false)
}