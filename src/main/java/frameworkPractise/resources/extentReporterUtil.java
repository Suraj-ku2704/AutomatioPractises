package frameworkPractise.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReporterUtil {

	private static ExtentReports extent;

	public static ExtentReports extendReportConfig() {

		if (extent == null) {
			String path = System.getProperty("user.dir") + "//reports//index.html";

			ExtentSparkReporter reporter = new ExtentSparkReporter(path);

			reporter.config().setReportName("Smoke Test");
			reporter.config().setDocumentTitle("Test Results");

			extent = new ExtentReports();
			extent.attachReporter(reporter);

			extent.setSystemInfo("Tester", "Suraj");
		}

		return extent;

	}

}
