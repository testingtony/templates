package templates.selenium.utils;

import org.hamcrest.*;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IsLoaded<T extends LoadableComponent<T>> extends DiagnosingMatcher<T>{

    @Override
    protected boolean matches(Object item, Description mismatchDescription) {
        Logger logger = LoggerFactory.getLogger(IsLoaded.class);
        try {
            Method m = item.getClass().getDeclaredMethod("isLoaded");
            m.setAccessible(true);
            m.invoke(item);
        } catch (NoSuchMethodException e) {
            logger.error("Missing isLoaded", e);
        } catch (InvocationTargetException e) {
            mismatchDescription.appendText("was not Loaded (threw '" + e.getCause().getLocalizedMessage() +"')");
            return false;
        } catch (Exception e) {
            logger.error("Unexpected failure", e);
            return false;
        }
        mismatchDescription.appendText("was Loaded");
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("was Loaded");
    }


    public static Matcher<LoadableComponent> isLoaded() {
        return new IsLoaded();
    }

    public static Matcher<LoadableComponent> isNotLoaded() {
        return not(isLoaded());
    }

}
