package gov.nist.hit.elr.quest.plugin.cs;

import java.util.Arrays;
import java.util.Collections;

import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class ELR_040 {

	/**
	 * ELR-040: OBR-3 (Filler Order Number) SHALL NOT contain the same value as
	 * another occurrence of OBR-3 (Filler Order Number) in the message.
	 */

	/**
	 * @param context the root of the message
	 * @return true if the assertion passes, false otherwise
	 */
	public boolean assertion(Element context) {
		// OBR-3 is located under PATIENT_RESULT.ORDER_OBSERVATION.OBR
		// OBR-3 path from the message root is 3[*].2[*].2[*].3[*]
		List<Element> OBR3List = Query.query(context, "3[*].2[*].2[*].3[*]").get();
		Iterator<Element> it = OBR3List.iterator();
		java.util.List<java.util.List<String>> values = new java.util.ArrayList<java.util.List<String>>();
		while (it.hasNext()) {
			Element next = it.next();

			// extracting the OBR-3 components
			List<Simple> OBR3_1List = Query.queryAsSimple(next, "1[1]").get();
			List<Simple> OBR3_2List = Query.queryAsSimple(next, "2[1]").get();
			List<Simple> OBR3_3List = Query.queryAsSimple(next, "3[1]").get();
			List<Simple> OBR3_4List = Query.queryAsSimple(next, "4[1]").get();

			// extracting the values as String. That components do not repeat so each list
			// should only have one element. If that is not the case, other validation
			// mechanisms will catch that. We only focus on the first element of the List
			String OBR3_1 = OBR3_1List.size() > 0 ? OBR3_1List.apply(0).value().raw() : "";
			String OBR3_2 = OBR3_2List.size() > 0 ? OBR3_2List.apply(0).value().raw() : "";
			String OBR3_3 = OBR3_3List.size() > 0 ? OBR3_3List.apply(0).value().raw() : "";
			String OBR3_4 = OBR3_4List.size() > 0 ? OBR3_4List.apply(0).value().raw() : "";

			// creating a List with all the OBR-3 values
			java.util.List<String> OBR3 = Arrays.asList(OBR3_1, OBR3_2, OBR3_3, OBR3_4);
			values.add(OBR3);
		}
		return check(values);
	}

	/**
	 * @param actual The list of OBR-3 values
	 * @return true if each OBR-3 is unique
	 */
	public boolean check(java.util.List<java.util.List<String>> actual) {
		// empty list or list with only one element passes immediately
		if (actual == null || actual.size() == 0 || actual.size() == 1) {
			return true;
		}
		long count = actual.stream().filter(e -> Collections.frequency(actual, e) > 1).distinct().count();
		return count == 0;
	}
}
