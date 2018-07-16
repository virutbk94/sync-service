package shippo.sync_check.rider_service.actor;

import shippo.global.Mapping;
import shippo.global.entities.v0.rider.TookanAgent;
import shippo.global.entities.v1.UserIntegrationAccount;
import shippo.sync_check.sync_check_common.actor.AbstractSync;
import shippo.sync_check.sync_check_common.constant.ErrorCode;

import java.util.List;

public class UserIntergrationAcountTookanAgentActor extends AbstractSync<UserIntegrationAccount, TookanAgent> {

    public UserIntergrationAcountTookanAgentActor(String firstDatabase, String secondDatabase) {
        super(UserIntegrationAccount.class, TookanAgent.class, firstDatabase, secondDatabase);
    }

    @Override
    public int syncCheck(List<UserIntegrationAccount> firstTableData, List<TookanAgent> secondTableData) {
        int rs = ErrorCode.OK;
        StringBuilder builder = new StringBuilder();
        builder.append("UserIntegrationAccount to TookanAgent : ");
        // UserIntegrationAccount to TookanAgent
        for (UserIntegrationAccount userIntegrationAccount : firstTableData) {
            TookanAgent tookanAgent = Mapping.mapUserInterg2Tookan(userIntegrationAccount);
            if (!secondTableData.contains(tookanAgent)) {
                builder.append(userIntegrationAccount.getIntegrationId() + ",");
                rs = ErrorCode.DATA_NOT_MATCH;
            }
        }

        builder.append("\nTookanAgent to UserIntegrationAccount : ");
        // TookanAgent to UserIntegrationAccount
        for (TookanAgent tookanAgent : secondTableData) {
            UserIntegrationAccount userIntegrationAccount = Mapping.mapTookan2UserIntergration(tookanAgent);
            if (!firstTableData.contains(userIntegrationAccount)) {
                builder.append(tookanAgent.getId() + ",");
                rs = ErrorCode.DATA_NOT_MATCH;
            }
        }
        errorNotify = builder.toString();
        return rs;
    }

}
