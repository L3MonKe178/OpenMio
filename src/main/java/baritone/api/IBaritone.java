package baritone.api;
import baritone.api.behavior.IPathingBehavior;
import baritone.api.event.listener.IEventBus;
import baritone.api.command.IBaritoneChatControl;
import baritone.api.process.IPathingControlManager;
import baritone.api.process.ICustomGoalProcess;
import baritone.api.process.IElytraProcess;
import baritone.api.selection.ISelectionManager;
public interface IBaritone {
   IPathingBehavior getPathingBehavior();
   IEventBus getGameEventHandler();
   IBaritoneChatControl getBaritoneChatControl();
   IPathingControlManager getPathingControlManager();
   ICustomGoalProcess getCustomGoalProcess();
   IElytraProcess getElytraProcess();
   ISelectionManager getSelectionManager();
}
