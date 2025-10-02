package com.example.test.service;

public class DuplicateCheckResponse {
        private Object checkedArray;
        private boolean hasDuplicates;
        private String message;

        public DuplicateCheckResponse(Object checkedArray, boolean hasDuplicates, String message) {
            this.checkedArray = checkedArray;
            this.hasDuplicates = hasDuplicates;
            this.message = message;
        }

        // Getters and setters
        public Object getCheckedArray() { return checkedArray; }
        public void setCheckedArray(Object checkedArray) { this.checkedArray = checkedArray; }

        public boolean isHasDuplicates() { return hasDuplicates; }
        public void setHasDuplicates(boolean hasDuplicates) { this.hasDuplicates = hasDuplicates; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }