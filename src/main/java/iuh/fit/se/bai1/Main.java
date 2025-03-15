package iuh.fit.se.bai1;

public class Main {
    public static void main(String[] args) {
        // Tạo cấu trúc thư mục
        Directory rootDir = new Directory("Root", "/root");

        // Tạo thư mục con
        Directory documentsDir = new Directory("Documents", "/root/Documents");
        Directory picturesDir = new Directory("Pictures", "/root/Pictures");

        // Tạo các tập tin trong thư mục Documents
        File resume = new File("Resume.docx", 1024, "/root/Documents/Resume.docx");
        File report = new File("Report.pdf", 2048, "/root/Documents/Report.pdf");

        // Thêm tập tin vào thư mục Documents
        documentsDir.add(resume);
        documentsDir.add(report);

        // Tạo thư mục con trong Pictures
        Directory vacationDir = new Directory("Vacation", "/root/Pictures/Vacation");

        // Tạo các tập tin trong thư mục Vacation
        File photo1 = new File("Photo1.jpg", 3072, "/root/Pictures/Vacation/Photo1.jpg");
        File photo2 = new File("Photo2.jpg", 2560, "/root/Pictures/Vacation/Photo2.jpg");

        // Thêm tập tin vào thư mục Vacation
        vacationDir.add(photo1);
        vacationDir.add(photo2);

        // Thêm thư mục Vacation vào thư mục Pictures
        picturesDir.add(vacationDir);

        // Thêm tập tin trực tiếp vào thư mục Pictures
        File screenshot = new File("Screenshot.png", 1536, "/root/Pictures/Screenshot.png");
        picturesDir.add(screenshot);

        // Thêm tất cả vào thư mục gốc
        rootDir.add(documentsDir);
        rootDir.add(picturesDir);

        // Hiển thị toàn bộ cấu trúc thư mục
        rootDir.display(0);

        // Hiển thị thông tin về kích thước tổng cộng của thư mục
        System.out.println("\nTotal size of root directory: " + rootDir.getSize() + " bytes");
        System.out.println("Total size of documents directory: " + documentsDir.getSize() + " bytes");
        System.out.println("Total size of pictures directory: " + picturesDir.getSize() + " bytes");
        System.out.println("Total size of vacation directory: " + vacationDir.getSize() + " bytes");

    }
}