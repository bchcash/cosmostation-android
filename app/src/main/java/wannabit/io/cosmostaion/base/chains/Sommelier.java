package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Sommelier extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.SOMMELIER_MAIN; }
    public int chainImg() { return R.drawable.chain_sommelier; }
    public int chainInfoImg() { return R.drawable.infoicon_sommelier; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_sommelier; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_sommelier; }
    public int chainColor() { return R.color.color_sommelier; }
    public int chainBgColor() { return R.color.colorTransBgSommelier; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_sommelier; }
    public String chainName() { return "sommelier"; }
    public String chainKoreanName() { return "소믈리에"; }
    public String chainIdPrefix() { return "sommelier-"; }

    public int mainDenomImg() { return R.drawable.token_sommelier; }
    public String mainDenom() { return "usomm"; }
    public String addressPrefix() { return "somm"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "grpc-sommelier.cosmostation.io"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "sommelier/"; }
    public String homeInfoLink() { return  "https://sommelier.finance/"; }
    public String blogInfoLink() { return  "https://medium.com/@sommelierfinance"; }
    public String coingeckoLink() { return  COINGECKO_URL + "sommelier"; }
}
