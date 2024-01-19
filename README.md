# AppStore Desktop Application

## Description
This project is a Java Swing-based desktop application that simulates an app store. It allows users to browse, download, and manage applications. The application supports different user roles, including admins and regular users, with distinct functionalities for each.

## Features
- **User Authentication**: Includes user login and signup functionalities.
- **Change Password**: All users can change their password
- **User Roles**: Two types of users - Admins and Regular Users.
  - **Admins** can add or remove products.
  - **Regular Users** can view apps, download them, and uninstall them.
- **App Management**: Admins can manage the applications available in the AppStore.
- **Stylish UI**: User-friendly interface with consistent design patterns.

  ## Requirements that are met
- **MVC Architecture**: Developed using the Model-View-Controller (MVC) architectural pattern.
- **Inheritance, Override & Overload**: done in controller, Controller is the parent class that has two methods with the same name but different parameters (changeView()). Inheritance is also in views.
- **Enums**: Categories from the database are translated into Categories enum.
- **Min 5 views**: Including login, sign up, home, add apps, change password, view downloaded
- **User Roles**: Two types of users - Admins and Regular Users.
- **Exception Handling**: Robust error and exception handling implemented.
- **Code Style**: decent
- **Database Integration**: Uses PostgreSQL to store and manage data.
- **C**: user, app
- **R**: user, apps installed, all apps, categories
- **U**: password
- **D**: delete from installed apps
- **Documentation**: this

## Usage
After launching the application:
1. Users must sign up or log in.
2. Browse through the available applications.
3. For admins, use the admin panel to manage apps.
4. Regular users can download or uninstall apps as desired.

## Dependencies
- Java JDK coretto 17.0.8_7
- PostgreSQL postgresql-42.7.1.jar

## Authors
- Muresan Andrei-Ioan 30425
