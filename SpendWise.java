/*
                 SpendWise
         TRACK TODAY - BETTER TOMORROW
    A small Expense Tracker for a Smarter You!
*/

import java.util.Scanner;

public class SpendWise {

  // global vars
  static final int MAX_LIMIT = 100;
  static double[] amounts = new double[MAX_LIMIT];
  static String[] categories = new String[MAX_LIMIT];
  static String[] descriptions = new String[MAX_LIMIT];
  static int expenseCount = 0;
  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int userChoice;
    System.out.println("=== Welcome to SpendWise ===");

    do {
      // action-menu
      System.out.println("1. Add Expense");
      System.out.println("2. View All Expenses");
      System.out.println("3. View Total Expenses");
      System.out.println("4. View Highest Expense");
      System.out.println("5. View Lowest Expense");
      System.out.println("6. View Expenses by Category");
      System.out.println("7. Search Expense");
      System.out.println("8. Exit");
      System.out.print("Enter your choice: ");
      userChoice = sc.nextInt();

      switch (userChoice) {
        case 1: // add expense
          addExpense();
          break;
        case 2: // all expenses
          viewAllExpenses();
          break;
        case 3: // total expense
          totalExpense();
          break;
        case 4: // highest expense
          viewHighestExpense();
          break;
        case 5: // lowest expense
          viewLowestExpense();
          break;
        case 6: // expense by category
          viewExpensesByCategory();
          break;
        case 7: //search expense by description
          searchExpenseByDescription();
          break;
        case 8: // exit
          exitNote();
          break;
        default: // incorrect choice
          System.out.println("Invalid choice. Please try again!");
      }
    } while (userChoice != 8);
  }

  static void addExpense() {
    if (expenseCount >= MAX_LIMIT) {
      System.out.println("No more expense can be added!");
      return;
    }

    double newExpenseAmount;
    String newExpenseCategory;
    String newExpenseDescription;
    System.out.print("\tEnter amount to be added: ");
    newExpenseAmount = sc.nextDouble();
    sc.nextLine();
    if (newExpenseAmount <= 0) {
      showError("Invalid Expense Amount!");
      return;
    }

    System.out.print("\tEnter category: ");
    newExpenseCategory = sc.nextLine();
    if (newExpenseCategory.trim().isEmpty()) {
      showError("Category is Required!");
      return;
    }

    System.out.print("\tEnter description: ");
    newExpenseDescription = sc.nextLine();
    if (newExpenseDescription.trim().isEmpty()) {
      showError("Description is Required!");
      return;
    }

    amounts[expenseCount] = newExpenseAmount;
    categories[expenseCount] = newExpenseCategory;
    descriptions[expenseCount] = newExpenseDescription;
    System.out.println("\tExpense Added Successfully!\n");
    expenseCount++;
  }

  static void viewAllExpenses() {
    if (expenseCount == 0) {
      showError("No Expense Found!");
      return;
    }
    System.out.println("\t--- Your Expenses ---");
    for (int i = 0; i < expenseCount; i++) {
      viewExpense(i);
    }
    System.out.println("\tEnd of Expense History...\n");
  }

  static void viewExpense(int idx) {
    System.out.printf(
        "\t %-5d %-12.2f %-15s %-20s%n",
        (idx + 1), amounts[idx], categories[idx], descriptions[idx]
    );
  }

  static void totalExpense() {
    double totalExpense = 0.0;
    for (int i = 0; i < expenseCount; i++) {
      totalExpense += amounts[i];
    }
    System.out.println("\t--- Your Total Expenses ---");
    System.out.printf("\t%.2f \n\n", totalExpense);
  }

  static void viewHighestExpense() {
    if (expenseCount == 0) {
      showError("No Expense Found!");
      return;
    }
    int highestExpenseIdx = 0;
    for (int i = 1; i < expenseCount; i++) {
      if (amounts[i] > amounts[highestExpenseIdx])
        highestExpenseIdx = i;
    }
    viewExpense(highestExpenseIdx);
    System.out.println();
  }

  static void viewLowestExpense() {
    if (expenseCount == 0) {
      showError("No Expense Found!");
      return;
    }
    int lowestExpenseIdx = 0;
    for (int i = 1; i < expenseCount; i++) {
      if (amounts[i] < amounts[lowestExpenseIdx])
        lowestExpenseIdx = i;
    }
    viewExpense(lowestExpenseIdx);
    System.out.println();
  }

  static void viewExpensesByCategory() {
    if (expenseCount == 0) {
      showError("No Expense Found!");
      return;
    }

    sc.nextLine();
    int countMatches = 0;
    double totalCategoryExpense = 0;
    String searchCategory;
    System.out.print("Enter Category: ");
    searchCategory = sc.nextLine();

    if (searchCategory.trim().isEmpty()) {
      showError("Category is Required to Initiate Search!");
      return;
    }

    for (int i = 0; i < expenseCount; i++) {
      if (categories[i].equalsIgnoreCase(searchCategory)) {
        viewExpense(i);
        countMatches++;
        totalCategoryExpense += amounts[i];
      }
    }

    if (countMatches == 0) {
      showError("No Match Found!");
    } else {
      System.out.printf("\t%d matches found!\n", countMatches);
      System.out.printf("\tTotal Category Expense: %.2f", totalCategoryExpense);
    }

    System.out.println();
  }

  static void searchExpenseByDescription() {
    if (expenseCount == 0) {
      showError("No Expense Found!");
      return;
    }

    sc.nextLine();
    int countMatches = 0;
    String searchDescription;
    System.out.print("Enter description: ");
    searchDescription = sc.nextLine();

    if (searchDescription.trim().isEmpty()) {
      showError("Description is Required to Initiate Search!");
      return;
    }

    for (int i = 0; i < expenseCount; i++) {
      if (descriptions[i].toLowerCase().contains(searchDescription.toLowerCase())) {
        viewExpense(i);
        countMatches++;
      }
    }

    if (countMatches == 0) {
      showError("No Match Found!");
    } else {
      System.out.printf("\t%d results found!", countMatches);
    }

    System.out.println();
  }

  static void exitNote() {
    System.out.println("\t=== Thank you for using SpendWise application! ===\n\t=== Hope to see you again! ===");
  }

  static void showError(String msg) {
    System.out.printf("\n\t--- ERROR! %s ---\n\n", msg);
  }
}