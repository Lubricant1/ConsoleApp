package com.company.controller;

import com.company.entity.ResultEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface Controller {

    List<ResultEntity> scanFoldersList(List<String> pathsList) throws InterruptedException, ExecutionException;

}
