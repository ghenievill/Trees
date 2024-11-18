import java.util.ArrayList;
import java.util.Scanner;

public class FamilyTreeManager {
    static class TreeNode {
        String name;
        ArrayList<TreeNode> children = new ArrayList<>();
        TreeNode(String name) { this.name = name; }
    }
    static TreeNode root;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Family Tree Manager");
            System.out.println("\n1. Add Family Member\n2. Display Family Tree\n3. Search for Member\n4. Delete Member\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt(); 
            scanner.nextLine();  

            switch (choice) {
                case 1: addMember(scanner); break;
                case 2: displayTree(root, 0); break;
                case 3: searchMember(scanner); break;
                case 4: deleteMember(scanner); break;
                case 5: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    static void addMember(Scanner scanner) {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter parent name (leave empty for root): ");
        String parentName = scanner.nextLine();
        TreeNode newMember = new TreeNode(name);

        if (parentName.isEmpty()) {
            root = newMember;
            System.out.println("Added " + name + " as root.");
        } else {
            TreeNode parent = findMember(root, parentName);
            if (parent != null) {
                parent.children.add(newMember);
                System.out.println("Added " + name + " under " + parentName);
            } else {
                System.out.println("Parent not found.");
            }
        }
    }

    static void displayTree(TreeNode node, int level) {
        if (node != null) {
            System.out.println("  ".repeat(level) + "- " + node.name);
            node.children.forEach(child -> displayTree(child, level + 1));
        }
    }

    static void searchMember(Scanner scanner) {
        System.out.print("Enter member name to search: ");
        String name = scanner.nextLine();
        TreeNode member = findMember(root, name);
        if (member != null) {
            System.out.println("Found: " + member.name);
            member.children.forEach(child -> System.out.println("  - " + child.name));
        } else {
            System.out.println("Member not found.");
        }
    }

    static void deleteMember(Scanner scanner) {
        System.out.print("Enter member name to delete: ");
        String name = scanner.nextLine();
        if (root != null && root.name.equals(name)) {
            root = null;
            System.out.println("Deleted root member " + name);
        } else if (deleteNode(root, name)) {
            System.out.println("Deleted :" + name);
        } else {
            System.out.println("Member not found.");
        }
    }

    static TreeNode findMember(TreeNode node, String name) {
        if (node == null || node.name.equals(name)) return node;
        for (TreeNode child : node.children) {
            TreeNode found = findMember(child, name);
            if (found != null) return found;
        }
        return null;
    }

    static boolean deleteNode(TreeNode node, String name) {
        for (TreeNode child : node.children) {
            if (child.name.equals(name)) {
                node.children.remove(child);
                return true;
            }
            if (deleteNode(child, name)) return true;
        }
        return false;
    }
}
