package com.raghv.demo.sezzle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


@RestController
public class ServiceController {

    private SimpMessagingTemplate template;

    private ConcurrentLinkedQueue<Item> resultQueue = new ConcurrentLinkedQueue<Item>();



    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    public ServiceController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @RequestMapping(path = "/result", method = RequestMethod.POST)
    public void computeResult(@RequestBody Map<String, Object> payLoad) throws Exception {
        Item newItem = new Item(System.currentTimeMillis(), (String) payLoad.get("total"));
        resultQueue.add(newItem);
        this.template.convertAndSend("/topic/message", newItem);
        executorService.execute(() -> {
            while (resultQueue.size() > 10){
                resultQueue.remove();
            }
        });
    }



    @GetMapping(path = "/load")
    public Item[] loadComputedItems() {
        /**
         <p>Beware that, unlike in most collections, this method is
         * <em>NOT</em> a constant-time operation.So it results the results of that time.But
         * subsequent added live elements will be added on top
         */

        int resultSize = resultQueue.size();
        Object[] arr = resultQueue.toArray();
        Item[] returnArray = new Item[resultSize > 10 ? 10 : resultSize];
        int stopPoint = resultSize - 10 > 0 ?  (resultSize - 10) : 0;


        for(int i=0,j=resultSize-1; j >= stopPoint;i++,j--)
        {
            returnArray[i] = (Item)arr[j];
        }

        return returnArray;

    }




}
