package zxc.rpc.zxcrpcremote.remotingtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Message implements Serializable {
    private String content;
}
