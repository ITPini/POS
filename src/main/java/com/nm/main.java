package com.nm;

class main {

    public static final LoginForm loginForm = new LoginForm();
    public static final POSForm POSForm = new POSForm();
    public static String currentUser;

    public static void main(String[] args) {
        POSForm.start();
    }
}
