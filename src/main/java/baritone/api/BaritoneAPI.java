package baritone.api;

public class BaritoneAPI {
    private static final Settings SETTINGS = new Settings();
    private static final IBaritoneProvider PROVIDER = new IBaritoneProvider() {
        @Override public IBaritone getPrimaryBaritone() { return null; }
    };
    public static IBaritoneProvider getProvider() { return PROVIDER; }
    public static Settings getSettings() { return SETTINGS; }

    public interface IBaritoneProvider {
        IBaritone getPrimaryBaritone();
    }
}
