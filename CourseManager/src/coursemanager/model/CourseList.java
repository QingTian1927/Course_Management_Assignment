package coursemanager.model;

public class CourseList extends CommonList<Course> {

    public void addLast(Course course) {
        super.addLast(course);
    }

    public void addFirst(Course course) {
        super.addFirst(course);
    }

    public void insertAfterIndex(int k, Course course) {
        this.insert(k + 1, course);
    }

    public void deleteToIndex(int k) {
        if (k >= this.size()) {
            this.clear();
        } else {
            this.head = this.getByIndex(k + 1);
        }
    }

    public Node<Course> searchByCcode(String Ccode) {
        Node<Course> temp = this.head;
        while (temp != null) {
            if (temp.data.getCcode().equals(Ccode)) {
                return temp;
            }
            temp = temp.next;
        }
        System.out.println("It not found");
        return null;
    }

    public CourseList searchByName(String sname) {
        CourseList a = new CourseList();
        Node<Course> temp = this.head;
        while (temp != null) {
            if (temp.data.getSname().equals(sname)) {
                a.addLast(temp.data);
            }
            temp = temp.next;
        }
        return a;
    }

    public void deleteByCcode(String Ccode) {
        if (this.head == null) {
            System.out.println("No course to delete");
        }
        if (this.searchByCcode(Ccode).data.getRegistered() == 0) {
            this.delete(this.searchByCcode(Ccode));
        } else {
            System.out.println("this course has been registered");
        }
    }

    public CommonList<Course> sort() {
        CourseList a = new CourseList();
        if (this.head == null) {
            return a;
        }
        Node<Course> temp = this.head;
        while (temp != null) {
            a.addLast(temp.data);
            temp = temp.next;
        }
        Node<Course> p = a.head;
        while (p != null) {
            Node<Course> q = p.next;
            while (q != null) {
                if (p.data.getCcode().compareTo(q.data.getCcode()) < 0) {
                    this.swap(p, q);
                }
                q = q.next;
            }
            p = p.next;
        }
        return a;
    }

    public void display() {
        if (this.head == null) {
            System.out.println("No course yet");
            return;
        }
        Node<Course> temp = this.head;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }
}