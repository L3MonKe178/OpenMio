package baritone.api.behavior;
import baritone.api.pathing.goals.Goal;
public interface IPathingBehavior {
   void setGoalAndPath(Goal goal);
   void cancelEverything();
   void forceCancel();
   boolean isPathing();
   Goal getGoal();
}
