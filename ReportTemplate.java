abstract class ReportGenerator {
    public final void generateReport() {
        fetchData();
        formatReport();
        exportReport();
    }
    protected abstract void fetchData();
    protected abstract void formatReport();
    protected abstract void exportReport();
}

class PDFReport extends ReportGenerator {
    protected void fetchData() { System.out.println("Fetching data for PDF"); }
    protected void formatReport() { System.out.println("Formatting PDF report"); }
    protected void exportReport() { System.out.println("Exporting as PDF"); }
}

class ExcelReport extends ReportGenerator {
    protected void fetchData() { System.out.println("Fetching data for Excel"); }
    protected void formatReport() { System.out.println("Formatting Excel report"); }
    protected void exportReport() { System.out.println("Exporting as Excel"); }
}
