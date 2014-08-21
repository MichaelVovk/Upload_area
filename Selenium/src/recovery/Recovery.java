package recovery;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import static org.junit.Assert.*;

public class Recovery {

	public Recovery(String recoveryHandler) {

		float similarity = (float) 0.8;
		boolean bResult = false;
		int timeout = 5;

		if (recoveryHandler.compareToIgnoreCase("Ads") == 0) {
			Pattern warninglink = new Pattern("patterns/recovery/Ads/warning.png");
			Pattern gotohomepage = new Pattern("patterns/recovery/Ads/link.png");
			Screen screen = new Screen();
			if (screen.exists(warninglink.similar(similarity), timeout) != null) {
				try {
					screen.click(gotohomepage, 0);
					bResult = true;
				} catch (FindFailed e) {
					e.printStackTrace();
					bResult = false;
				}
			}
		}

		assertTrue(bResult);
	}

}
