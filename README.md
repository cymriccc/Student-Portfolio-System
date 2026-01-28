Student Portfolio Builder System
A desktop-based management system designed for students to organize, build, and sync their academic portfolios. Built with Java Swing and integrated with MySQL for persistent data management.

Note: This project is currently in Active Development. Features are being added and refined as part of my BSIT-MWA coursework at National University.

🚀 Features
Secure Authentication: Login and Registration system with hashed credentials stored in MySQL.

Dynamic Dashboard: Real-time greeting and course/year level display synced with user data.

Profile Synchronization: Ability to update personal information (Full Name, Student ID, Email, Bio) and reflect changes instantly across the UI.

Custom UI Components: Styled using a custom "Sage and Cream" aesthetic with custom-built Dialog boxes and window controls.

🛠️ Tech Stack
Language: Java

GUI Toolkit: Java Swing

Database: MySQL

Database Connectivity: JDBC (MySQL Connector/J)

📂 Project Structure
Plaintext
src/
 ├── db/            # Database connection and configuration
 ├── gui/           # UI Components (LoginForm, MainContent, ProfilePanel, etc.)
 └── assets/        # Icons, images, and brand assets
🏗️ Installation & Setup
Clone the repo:

Bash
git clone https://github.com/yourusername/your-repo-name.git
Database Configuration:

Import the provided SQL schema into your MySQL instance.

Update db/Database.java with your local credentials (DB Name, Username, Password).

Run the Application:

Open the project in your preferred IDE (VS Code/IntelliJ/NetBeans).

Run LoginForm.java to start the application.

📝 Roadmap (Planned Features)
[ ] Image Uploading for Avatars (Currently in testing)

[ ] Portfolio template export (PDF/JSON)

[ ] Settings panel for theme customization

[ ] Drifting-themed mini-game integration (Optional)

⚖️ License
This project is licensed under the MIT License — see the LICENSE file for details.
