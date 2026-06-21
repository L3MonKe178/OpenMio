package baritone.api.process;
import baritone.api.pathing.goals.Goal;
public interface ICustomGoalProcess extends IBaritoneProcess {
   void setGoalAndPath(Goal goal);
   void setGoal(Goal goal);
}
