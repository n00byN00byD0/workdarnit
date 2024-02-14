package xyz.xyz.xyz.workdarnit.account;

import org.bouncycastle.jcajce.provider.digest.SHA3;

import java.util.Arrays;

/**
 * Interface for the login process &
 * an only semi serious design of a login system using secure hashing functions.
 * Good practice would dictate splitting up the interface but as my purpose here
 * is just illustrative I'm NOT going to do things the best way ATM.
 */

public interface Login {

    /*
     * Originally I thought I'd do something smart with longs -
     * - the following* code may get snipped?!
     */
    long BAD_LONG = -1;
    long BAD_LONG_SUBSTITUTE = 0xB00B135L;
    // *see above

    default byte[] secureHash(byte[] bytes) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
        return digestSHA3.digest(bytes);
    }

    default long hashReduce(byte[] hashBytes) {
        final int steps = hashBytes.length % 8 == 0 ?
                hashBytes.length / 8 :
                hashBytes.length / 8 + 1;
        final byte[] hb = Arrays.copyOf(hashBytes, steps * 8);
        long out = 0;
        for (int at=0; at<steps; at++) {
            long chunk = hb[at*8] | hb[at*8 + 1] << 8 | hb[at*8 + 2] << 16 |
                    ((long) hb[at*8 + 3]) << 24 | ((long) hb[at*8 + 4]) << 32 |
                    ((long) hb[at*8 + 5]) << 40 | ((long) hb[at*8 + 6]) << 48 |
                    ((long) hb[at*8 + 7]) << 56;
            out ^= chunk;
        }
        if (out == BAD_LONG) {
            out = BAD_LONG_SUBSTITUTE;
        }
        return out;
    }

    boolean queryName(long nameRef);
    boolean queryPassword(long nameRef, long pwdRef);

    // Separating testing from linking access to the rest of the program 🤷
    Access login(long nameRef, long passRef);

    default Access login(byte[] name, byte[] password) throws LoginException {
        final long nameRef = hashReduce(secureHash(name)),
                pwdRef = hashReduce(secureHash(password));
        final boolean failureName = !queryName(nameRef);
        if (failureName) throw new LoginException(true, false);
        final boolean failurePwd = !queryPassword(nameRef, pwdRef);
        if (failurePwd) throw new LoginException(false, true);
        return login(nameRef, pwdRef);
    }
}
