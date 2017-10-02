import static org.junit.Assert.*;

import org.junit.*;

public class LaboonCoinTest {

    // Re-usable LaboonCoin reference.
    LaboonCoin _l;

    // Create a new LaboonCoin instance before each test.
    @Before
    public void setup() {
    _l = new LaboonCoin();
    }
    
    // Assert that creating a new LaboonCoin instance
    // does not return a null reference
    @Test
    public void testLaboonCoinExists() {
    assertNotNull(_l);
    }

    // Creating a block String with valid data should return
    // a valid block.  This includes printing data out
    // normally, the previous hash and current hash as a hex
    // representations of themself, and the nonce in hex
    // repretentation.  Values should be delimited by
    // pipes.
    @Test
    public void testCreateBlockValid() {
    int prevHash = 0x0;
    int nonce = 0x16f2;
    int hash = 0x545ac;
    String validBlock = _l.createBlock("kitten", prevHash, nonce, hash);
    assertEquals("kitten|00000000|000016f2|000545ac", validBlock);
    }

    // Trying to represent a blockchain which has no blocks
    // in it should just return an empty string.
    @Test
    public void testGetBlockChainNoElements() {
    // By default, _l.blockchain has no elements in it.
    // So we can just test it immediately.
    String blockChain = _l.getBlockChain();
    assertEquals("", blockChain);
    }
    

    // Viewing the blockchain as a full string which has valid
    // elements should include all of the elements.  Note that the
    // final element DOES have a trailing \n!
    @Test
    public void testGetBlockChainElements() {
    _l.blockchain.add("TESTBLOCK1|00000000|000010e9|000a3cd8");
    _l.blockchain.add("TESTBLOCK2|000a3cd8|00002ca8|0008ff30");
    _l.blockchain.add("TESTBLOCK3|0008ff30|00002171|0009f908");
    String blockChain = _l.getBlockChain();
    assertEquals("TESTBLOCK1|00000000|000010e9|000a3cd8\nTESTBLOCK2|000a3cd8|00002ca8|0008ff30\nTESTBLOCK3|0008ff30|00002171|0009f908\n", blockChain);
    }
        
    // TODO - PUT YOUR SIX TESTS HERE
    @Test
    public void testHashFirstString() {
    int hashValue = _l.hash("bill");
    String hex = Integer.toHexString(hashValue);
    assertEquals("53c4142c",hex);
    }

    @Test
    public void testHashSecondString() {
    int hashValue = _l.hash("laboon");
    String hex = Integer.toHexString(hashValue);
    assertEquals("4e4587d6",hex);    
    }

    @Test
    public void testHashEmpty() {
    int hashValue = _l.hash("");
    String hex = Integer.toHexString(hashValue);
    assertEquals("989680",hex); // Leading zeros are ignored
    }

    @Test
    public void testValidPass() {
    boolean testValid = _l.validHash(3,1);
    assertTrue(testValid);
    }

    @Test
    public void testValidFail() {
    boolean testValid = _l.validHash(3,160086131);
    assertFalse(testValid);
    }

    @Test
    public void testValidHighDifficulty() {
    boolean testValid = _l.validHash(8,100);
    assertFalse(testValid);
    }

}
