package net.cowfish.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.core.httpclient.ApacheHttpClientTransport;
import com.zhipu.oapi.service.v4.model.*;
import net.cowfish.common.ResponseWrapper;
import net.cowfish.dao.ParamMapper;
import net.cowfish.entity.AiRequest;
import net.cowfish.entity.ParamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 对接智谱的人工智能系统
 */
@RestController
public class AiController {
    @Value("${ai.api-key}")
    private String API_KEY;
    @Value("${ai.knowledge-id}")
    private String KNOWLEDGE_ID;


    /**
     * 同步调用
     */
    @PostMapping("ai/submit")
    public ResponseWrapper testInvoke(@RequestBody AiRequest aiRequest) {
        ClientV4 client = new ClientV4.Builder(API_KEY).build();
        List<ChatMessage> messages = new ArrayList();
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), aiRequest.getContent());
        messages.add(chatMessage);
//        String requestId = String.format(requestIdTemplate, System.currentTimeMillis());
        List<ChatTool> tools = new ArrayList<ChatTool>();
        ChatTool chatTool = new ChatTool();
        chatTool.setType("retrieval");
        Retrieval retrieval = new Retrieval();
        retrieval.setKnowledge_id(KNOWLEDGE_ID);
        chatTool.setRetrieval(retrieval);
        tools.add(chatTool);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()

                .model(Constants.ModelChatGLM4)
                .stream(Boolean.FALSE)
                .invokeMethod(Constants.invokeMethod)
                .messages(messages)
                .requestId(null)
                .tools(tools)
                .build();
        ModelApiResponse invokeModelApiResp = client.invokeModelApi(chatCompletionRequest);
        String s = "";
        try {
            s = JSON.toJSONString(invokeModelApiResp.getData().getChoices().get(0).getMessage().getContent());
            return ResponseWrapper.ok(s);
        } catch (Exception e) {
            e.printStackTrace();
            s = e.getMessage();
        }
        return ResponseWrapper.fail(s);
    }

}
