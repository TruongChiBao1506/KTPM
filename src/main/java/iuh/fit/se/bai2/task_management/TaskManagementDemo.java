package iuh.fit.se.bai2.task_management;

public class TaskManagementDemo {
    public static void main(String[] args) {
        // Tạo các công việc
        Task featureTask = new Task("Implement Login", "Create login functionality with OAuth");
        Task bugTask = new Task("Fix Homepage Crash", "Homepage crashes when user is not logged in");

        // Tạo các thành viên nhóm
        TeamMember developer = new TeamMember("Trương Chí Bảo", "Developer");
        TeamMember tester = new TeamMember("Nguyễn Văn Test", "QA Engineer");
        TeamMember manager = new TeamMember("Lê Thị Manager", "Project Manager");

        // Đăng ký theo dõi công việc
        featureTask.attach(developer);
        featureTask.attach(manager);
        bugTask.attach(developer);
        bugTask.attach(tester);
        bugTask.attach(manager);

        System.out.println("===== Thay đổi trạng thái công việc =====");

        // Thay đổi trạng thái công việc
        featureTask.setStatus(TaskStatus.IN_PROGRESS);
        bugTask.setStatus(TaskStatus.IN_PROGRESS);

        System.out.println("\n===== Tiếp tục cập nhật trạng thái =====");

        // Cập nhật tiếp trạng thái
        featureTask.setStatus(TaskStatus.UNDER_REVIEW);
        bugTask.setStatus(TaskStatus.COMPLETED);

        // Hủy đăng ký theo dõi
        System.out.println("\n===== Hủy đăng ký theo dõi =====");
        featureTask.detach(manager);
        System.out.println(manager.getRole() + " " + manager.getName() +
                " đã hủy đăng ký theo dõi công việc '" + featureTask.getName() + "'");

        // Thay đổi trạng thái sau khi hủy đăng ký
        featureTask.setStatus(TaskStatus.COMPLETED);
    }
}
