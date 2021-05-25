package com.wradchuk.springbootdockerandroidserver.service;

import com.google.gson.Gson;
import com.wradchuk.springbootdockerandroidserver.config.DataBaseConfig;
import com.wradchuk.springbootdockerandroidserver.core.*;
import com.wradchuk.springbootdockerandroidserver.models.TestModel;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;

@Service public class WebSocketMessageService {
    //public JDBC jdbc;


    public WebSocketMessageService() {
//        DataBaseConfig dataBaseConfig = new DataBaseConfig(
//                "user",
//                "pass",
//                "host",
//                "dbname"
//        );

        //this.jdbc = new JDBC(dataBaseConfig.getDataSource());

    }



    public void getTest(WebSocketSession session, RequestMessage request) throws IOException {
        //    { "command": "START", "message": { "testInteger": 1, "testString": "Vladimir" } }

        // Получаем данные запроса
        Object message = request.getMessage();

        // Создаём модель данных и можем с ней работать
        TestModel testModel = new Gson().fromJson(message.toString(), TestModel.class);

        //String usersModel = new Gson().toJson(jdbc.getUser(1));

        ResponseMessage response = new ResponseMessage(); // Создаём ответ для клиента
        response.setCommand(Commands.START); // Команда для клиента
       // response.setIndex(jdbc.getTableItemSize("users").toString()); // Индекс запроса для клиента (было когда-то для реализации очереди обработки)
        response.setData(testModel); // Просто вернём данные что отправил клиент

        session.sendMessage(new TextMessage(new Gson().toJson(response))); // Отправить клиенту ответ
    }


}