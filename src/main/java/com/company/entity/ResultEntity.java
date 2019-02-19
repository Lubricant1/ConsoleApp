package com.company.entity;

public class ResultEntity {
    private int subitemsCount;
    private String sourceFolderPath;

    public ResultEntity() {
    }

    public ResultEntity(int subitemsCount, String sourceFolderPath) {
        this.subitemsCount = subitemsCount;
        this.sourceFolderPath = sourceFolderPath;
    }

    public int getSubitemsCount() {
        return subitemsCount;
    }

    public void setSubitemsCount(int subitemsCount) {
        this.subitemsCount = subitemsCount;
    }

    public String getSourceFolderPath() {
        return sourceFolderPath;
    }

    public void setSourceFolderPath(String sourceFolderPath) {
        this.sourceFolderPath = sourceFolderPath;
    }
}
