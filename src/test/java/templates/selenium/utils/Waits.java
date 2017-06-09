package templates.selenium.utils;

import org.openqa.selenium.WebElement;

public class Waits {
    public static boolean waitUntilFound(WebElement... elements) {
        return waitUntilFound(20, elements);
    }

    public static boolean waitUntilFound(int timeOut, WebElement... elements) {
        long timeStarted = System.currentTimeMillis();
        long timeToWaitInMillis = timeOut * 1000L;

        for (WebElement element : elements) {
            while (true) {
                long timeNow = System.currentTimeMillis();
                if (timeNow - timeStarted > timeToWaitInMillis) {
                    return false;
                }

                String toString = element.toString();
                if (!toString.startsWith("Proxy element for: ")) {
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public static void pauseToLoad(){
        pause(500);
    }

    public static void pause() {
        pause(500);
    }

    public static void pause(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
