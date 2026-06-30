package hr.algebra.controller;

import hr.algebra.model.AppUser;

// Holds the currently logged-in user for the running application.
public final class AppSession {

    private static AppUser currentUser;

    private AppSession() {
    }

    // Stores the user after successful login.
    public static void setCurrentUser(AppUser user) {
        currentUser = user;
    }

    public static AppUser getCurrentUser() {
        return currentUser;
    }

    // Clears session data on logout or exit.
    public static void clear() {
        currentUser = null;
    }
}
