package com.company.controller.implementation;

import com.company.controller.Controller;
import com.company.entity.ResultEntity;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ApplicationController implements Controller {
    @Override
    public List<ResultEntity> scanFoldersList(List<String> pathsList) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<ResultEntity> resultList = executor.invokeAll(prepareTasks(pathsList))
            .stream()
            .map(simpleEntryFuture -> {
                try {
                    return simpleEntryFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .map(stringIntegerSimpleEntry ->
                new ResultEntity(stringIntegerSimpleEntry.getValue(), stringIntegerSimpleEntry.getKey()))
            .collect(Collectors.toList());
        executor.shutdown();
        return resultList;
    }

    private List<VisitorTask> prepareTasks(List<String> pathsList) {
        return pathsList.stream().map(VisitorTask::new).collect(Collectors.toList());
    }
}
