# AppStore Desktop Application

## Description
This project is a Java Swing-based desktop application that simulates an app store. It allows users to browse, download, and manage applications. The application supports different user roles, including admins and regular users, with distinct functionalities.

## Features
- **User Authentication**: Includes user login and signup functionalities.
- **Change Password**: With afferent checks (cannot change password into existing password etc.)
- **User Roles**: Two types of users - Admins and Regular Users.
  - **Admins** can add or remove products.
  - **Regular Users** can view apps, download them, and uninstall them.
- **Filter by category**
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
4. Regular users can "download" or "uninstall" apps as desired. Also, they can see what apps are installed. Cannot install apps twice.

##Some pictures

![Screenshot 2024-01-19 183203](https://github.com/andrei-muri/app-store/assets/120194940/18ea2e3c-bda8-460b-9331-b6009b510219)
![Screenshot 2024-01-19 183233](https://github.com/andrei-muri/app-store/assets/120194940/045a7531-191f-4d51-8a00-b1520fb8baf6)
![Screenshot 2024-01-19 183330](https://github.com/andrei-muri/app-store/assets/120194940/6a45ac64-7cd7-4c3d-906d-126a8c36f838)
![Screenshot 2024-01-19 183419](https://github.com/andrei-muri/app-store/assets/120194940/fae0f058-42a2-4872-a0fe-65eb19341de5)
![Screenshot 2024-01-19 183447](https://github.com/andrei-muri/app-store/assets/120194940/ea5008ec-10cd-44ab-b490-0b494b6081f6)
![Screenshot 2024-01-19 183506](https://github.com/andrei-muri/app-store/assets/120194940/bbb53cc9-4a21-490a-a822-c9d417cd9fb5)


## Dependencies
- Java JDK coretto 17.0.8_7
- PostgreSQL postgresql-42.7.1

## Authors
- Muresan Andrei-Ioan 30425
