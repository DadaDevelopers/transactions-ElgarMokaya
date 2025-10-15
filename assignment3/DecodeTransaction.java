import org.bitcoinj.core.*;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.script.Script;

public class DecodeTransaction {
    public static void main(String[] args) {
        // Use Bitcoin Mainnet parameters
        NetworkParameters params = MainNetParams.get();

        // The transaction hex 
        String txHex = "0200000000010131811cd355c357e0e01437d9bcf690df824e9ff785012b6115dfae3d8e8b36c10100000000fdffffff0220a107000000000016001485d78eb795bd9c8a21afefc8b6fdaedf718368094c08100000000000160014840ab165c9c2555d4a31b9208ad806f89d2535e20247304402207bce86d430b58bb6b79e8c1bbecdf67a530eff3bc61581a1399e0b28a741c0ee0220303d5ce926c60bf15577f2e407f28a2ef8fe8453abd4048b716e97dbb1e3a85c01210260828bc77486a55e3bc6032ccbeda915d9494eda17b4a54dbe3b24506d40e4ff43030e00";

        // Convert hex to byte array
        byte[] txBytes = Utils.HEX.decode(txHex);

        // Parse transaction
        Transaction tx = new Transaction(params, txBytes);

        System.out.println("Transaction ID: " + tx.getTxId());
        System.out.println("Version: " + tx.getVersion());
        System.out.println("LockTime: " + tx.getLockTime());
        System.out.println("Number of Inputs: " + tx.getInputs().size());
        System.out.println("Number of Outputs: " + tx.getOutputs().size());
        System.out.println("------------------------------------------------");

        // Print inputs
        System.out.println("Inputs:");
        for (TransactionInput input : tx.getInputs()) {
            System.out.println("  From tx: " + input.getOutpoint().getHash());
            System.out.println("  Output index: " + input.getOutpoint().getIndex());
            System.out.println("  ScriptSig: " + input.getScriptSig());
            System.out.println();
        }

        // Print outputs
        System.out.println("Outputs:");
        for (TransactionOutput output : tx.getOutputs()) {
            Coin value = output.getValue();
            Script script = output.getScriptPubKey();
            System.out.println("  Value: " + value.toFriendlyString());
            System.out.println("  ScriptPubKey: " + script);
            System.out.println();
        }
    }
}
