package security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MacBasedPRF implements PRF
{
    protected Mac mac;

    protected int hLen;

    protected String macAlgorithm;

    /**
     * Create Mac-based Pseudo Random Function.
     * 
     * @param macAlgorithm
     *            Mac algorithm to use, i.e. HMacSHA1 or HMacMD5.
     */
    public MacBasedPRF(String macAlgorithm)
    {
        this.macAlgorithm = macAlgorithm;
        try
        {
            mac = Mac.getInstance(macAlgorithm);
            hLen = mac.getMacLength();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    public MacBasedPRF(String macAlgorithm, String provider)
    {
        this.macAlgorithm = macAlgorithm;
        try
        {
            mac = Mac.getInstance(macAlgorithm, provider);
            hLen = mac.getMacLength();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
        catch (NoSuchProviderException e)
        {
            throw new RuntimeException(e);
        }
    }

    public byte[] doFinal(byte[] M)
    {
        byte[] r = mac.doFinal(M);
        return r;
    }

    public int getHLen()
    {
        return hLen;
    }

    public void init(byte[] P)
    {
        try
        {
            mac.init(new SecretKeySpec(P, macAlgorithm));
        }
        catch (InvalidKeyException e)
        {
            throw new RuntimeException(e);
        }
    }
}
