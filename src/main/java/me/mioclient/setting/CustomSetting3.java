package me.mioclient.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.function.Predicate;
import me.mioclient.setting.Setting;

public final class CustomSetting3<T extends Number> extends Setting<T> {
    public String field_4223 = "";

    public CustomSetting3(String string, T t, T t2, T t3, Predicate<T> predicate) {
        super(string, t, t2, t3, predicate);
    }

    public CustomSetting3(String string, T t, T t2, T t3) {
        super(string, t, t2, t3);
    }

    @SuppressWarnings("unchecked")
    public void method_2(Number n) {
        super.method_78((T) this.method_9(n));
    }

    public CustomSetting3<T> method_22(String string) {
        this.field_4223 = string;
        return this;
    }

    public String method_323() {
        return this.field_4223;
    }

    public Number method_9(Number t) {
        if (this.getValue() instanceof Double) {
            return t.doubleValue();
        }
        if (this.getValue() instanceof Integer) {
            return t.intValue();
        }
        if (this.getValue() instanceof Float) {
            return Float.valueOf((float) t.floatValue());
        }
        return t.longValue();
    }

    @Override
    public void method_78(String string) {
        if (this.getValue() instanceof Double) {
            this.method_2(Double.parseDouble(string));
        } else if (this.getValue() instanceof Integer) {
            this.method_2(Integer.parseInt(string));
        } else if (this.getValue() instanceof Float) {
            this.method_2(Float.valueOf((float) Float.parseFloat(string)));
        }
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive((Number) this.getValue());
    }

    @Override
    public void fromJson(JsonElement jsonElement) {
        if (this.getValue() instanceof Double) {
            this.method_2(jsonElement.getAsDouble());
        } else if (this.getValue() instanceof Integer) {
            this.method_2(jsonElement.getAsInt());
        } else if (this.getValue() instanceof Float) {
            this.method_2(Float.valueOf((float) jsonElement.getAsFloat()));
        }
    }
}
