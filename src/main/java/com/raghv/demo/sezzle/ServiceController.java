package com.raghv.demo.sezzle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


@RestController
public class ServiceController {

    private SimpMessagingTemplate template;

    private ConcurrentLinkedQueue<Item> resultQueue = new ConcurrentLinkedQueue<Item>();

    @Autowired
    public ServiceController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @RequestMapping(path = "/result", method = RequestMethod.POST)
    public void computeResult(@RequestBody Map<String, Object> payLoad) throws Exception {
        Item newItem = new Item(System.currentTimeMillis(), (String) payLoad.get("total"));
        resultQueue.add(newItem);

        while(resultQueue.size() > 10){
            Item removed = resultQueue.remove();

        }

        this.template.convertAndSend("/topic/message", newItem);
    }


    @GetMapping(path = "/load")
    public Item[] loadComputedItems() {

        int resultSize = resultQueue.size();
        Item[] returnArray = new Item[resultSize];
        Object[] arr = resultQueue.toArray();

        int stopPoint = resultSize - 10 > 0 ?  (resultSize - 10) : 0;

        for(int i=0,j=resultSize-1; j >= stopPoint;i++,j--)
        {
            returnArray[i] = (Item)arr[j];
        }

        return returnArray;

    }

}
