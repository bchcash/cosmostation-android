package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaWithdrawHardGrpcTask extends CommonTask {

    private Account                 mAccount;
    private BaseChain               mBaseChain;
    private String                  mDepositor;
    private ArrayList<Coin>         mWithdrawCoins;
    private String                  mMemo;
    private Fee                     mFees;
    private String                  mChainId;

    public KavaWithdrawHardGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String depositor,
                                         ArrayList<Coin> withdrawCoins, String memo, Fee fee, String chainId) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mDepositor = depositor;
        this.mWithdrawCoins = withdrawCoins;
        this.mMemo = memo;
        this.mFees = fee;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcKavaWithdrawHardReq(WKey.onAuthResponse(mBaseChain, mAccount), mDepositor, mWithdrawCoins, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId, mAccount.customPath, mBaseChain);
            ServiceOuterClass.BroadcastTxResponse response = txService.broadcastTx(broadcastTxRequest);
            mResult.resultData = response.getTxResponse().getTxhash();
            if (response.getTxResponse().getCode() > 0) {
                mResult.errorCode = response.getTxResponse().getCode();
                mResult.errorMsg = response.getTxResponse().getRawLog();
                mResult.isSuccess = false;
            } else {
                mResult.isSuccess = true;
            }

        } catch (Exception e) {
            WLog.e( "KavaHardDepositGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
