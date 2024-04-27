package zxc.rpc.learning.netty.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class RpcRequest {
    private String interfaceName;

    private String methodName;
}
