package org.dddjava.jig.presentation.view.poi.report.handler;

import org.dddjava.jig.presentation.view.report.ReportItem;

public interface ItemHandler {
    boolean canHandle(Object obj);

    String handle(ReportItem item, Object obj);
}
