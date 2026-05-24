/*
 * Project setup note:
 * Holds the currently authenticated user for the running application session.
 */

package hr.algebra.controller;

import hr.algebra.model.AppUser;

public final class AppSession {

    private static AppUser currentUser;

    private AppSession() {
    }

    /** Stores the logged-in user after successful authentication. */
    public static void setCurrentUser(AppUser user) {
        currentUser = user;
    }

    public static AppUser getCurrentUser() {
        return currentUser;
    }

    /** Clears session data on logout or application exit. */
    public static void clear() {
        currentUser = null;
    }
}
