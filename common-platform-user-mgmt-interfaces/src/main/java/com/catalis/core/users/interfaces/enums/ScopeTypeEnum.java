package com.catalis.core.users.interfaces.enums;

/**
 * Enum representing the scope types for roles in the system.
 * Based on the scope_type_enum in the database.
 */
public enum ScopeTypeEnum {
    /**
     * Represents a global scope that applies across the entire system.
     */
    GLOBAL,
    
    /**
     * Represents a branch-level scope.
     */
    BRANCH,
    
    /**
     * Represents a distributor-level scope.
     */
    DISTRIBUTOR
}