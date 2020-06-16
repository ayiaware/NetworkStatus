package com.example.networkstatus;



public interface OnConnectionListener {
    void notifyApplication(String state, String status, boolean response);
}
