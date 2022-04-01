# A Bookkeeping Application

## About this bookkeeping application

Money is inseparable from our daily life. Sometimes we spend, and sometimes we receive them.
Don't you want to know how much you spend every day, how much you earn, and where they are spent?

My bookkeeping application allows users to record their financial activities.
Features are:
- recording spending
- recording incomes
- calculating total spending
- calculating total incomes
- clearly displaying financial summary with date
- allowing to note where the money is spent

## Target user groups
This application will be more targeted to **young people** (such as college students) who have a small expenses but 
complex consumption fields and want to quickly summarize their financial 
situation and make a plan. The app can also be used by **middle-aged people** to keep track of daily expenses
(such as on clothes, groceries, etc.), along with their incomes to see if they earn more
or spent more.

However, it may not be suitable for *seniors* because users need to manually enter each of their expenses
and income so there is a chance of error, and seniors may have a greater chance of making mistakes on a less 
familiar application and device. Misunderstandings can be caused to older people if they don't know that a
mistake has been made.

## My interests on this project

The reason I am interested in this project is that I feel I am in need of such an app to record and summarize 
my financial activities. For me I spend different amounts every day on different things (food, clothes, books, etc. 
all cost money). I also have a part-time job, so I have a regular income. I wish I could design an app to log them, so 
I can figure out if I've saved up, and remind me if I need to change my lifestyle? Spending a little at a time doesn't 
get my attention, but summing them up will reveal whether I need to cut back.

## User Stories
- As a user, I want to be able to add a spending to my spending list.
- As a user, I want to be able to add an income to my income list.
- As a user, I want to be able to delete a spending from my spending list.
- As a user, I want to be able to delete an income from my income list.
- As a user, I want to be able to display my spending and income lists.
- As a user, I want to be able to calculate my total spending and income amount.
- As a user, I want to be able to categorize my spending.
- As a user, I want to be able to display the date.
- As a user, I want to be able to save my spending and income list.
- As a user, I want to be able to load my saved spending and income list from file.

## Phase 4: Task 2
Mon Mar 28 22:23:56 PDT 2022\
Added spending: Spending{amount=100.0, category='Food and Groceries', date='2022-March-28'}\
Mon Mar 28 22:24:26 PDT 2022\
Added spending: Spending{amount=90.0, category='Transportation', date='2022-March-29'}\
Mon Mar 28 22:24:34 PDT 2022\
Removed spending: Spending{amount=90.0, category='Transportation', date='2022-March-29'}\
Mon Mar 28 22:24:50 PDT 2022\
Added income: Income{amount=5000.0, date='2022-January-29'}\
Mon Mar 28 22:25:05 PDT 2022\
Added income: Income{amount=9000.0, date='2022-March-28'}\
Mon Mar 28 22:25:08 PDT 2022\
Removed income: Income{amount=5000.0, date='2022-January-29'}

## Phase 4: Task 3
- IncomeList and SpendingList are two very similar classes with a lot of repetitive code. I could make a new Class
called ObjectList, and pass Income and Spending to this class. By doing so, more features related with list can be added
 into this application without making more new classes.

- For both Spending and Income list, they have a method of calculating total
 amount. I could follow the Observer Pattern. Let SpendingList and IncomeList extends Subject, and delete
 calculateTotal method. Then, I need to create a new class called userUpdate which will implements 
 Observer, and implement the update method with the implementation of calculateTotal method from SpendingList or 
 IncomeList class. This will meet the Observer Design Pattern.
