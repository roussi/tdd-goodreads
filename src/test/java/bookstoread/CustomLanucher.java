package bookstoread;

import org.junit.platform.engine.TestEngine;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.PrintWriter;
import java.util.ServiceLoader;

/**
 * @author aroussi
 * @version %I% %G%
 */
public class CustomLanucher {

    public static void main(String[] args) {
        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener summaryGenListener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(summaryGenListener);
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectPackage("bookstoread"))
                .build();
        launcher.execute(request);
        summaryGenListener.getSummary().printTo(new PrintWriter(System.out));
    }
}
