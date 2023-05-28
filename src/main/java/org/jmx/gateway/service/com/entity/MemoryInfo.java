package org.jmx.gateway.service.com.entity;

public class MemoryInfo {
    private long used;
    private long committed;
    private long total;

    public MemoryInfo(long memory, long committed, long total) {
        this.used = memory;
        this.committed = committed;
        this.total = total;
    }

    // Getters and setters

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCommitted() {
        return committed;
    }

    public void setCommitted(long committed) {
        this.committed = committed;
    }
}
