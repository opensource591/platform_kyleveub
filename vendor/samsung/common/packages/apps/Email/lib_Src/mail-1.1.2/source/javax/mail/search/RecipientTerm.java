/*
 * RecipientTerm.java
 * Copyright (C) 2002 The Free Software Foundation
 * 
 * This file is part of GNU JavaMail, a library.
 * 
 * GNU JavaMail is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * GNU JavaMail is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * As a special exception, if you link this library with other files to
 * produce an executable, this library does not by itself cause the
 * resulting executable to be covered by the GNU General Public License.
 * This exception does not however invalidate any other reasons why the
 * executable file might be covered by the GNU General Public License.
 */

package javax.mail.search;

import javax.mail.Address;
import javax.mail.Message;

/**
 * A comparison of recipient headers in a message.
 *
 * @author <a href="mailto:dog@gnu.org">Chris Burdess</a>
 * @version 1.4
 */
public final class RecipientTerm
  extends AddressTerm
{

  /**
   * The recipient type.
   */
  protected Message.RecipientType type;

  /**
   * Constructor.
   * @param type the recipient type
   * @param address the address to match
   */
  public RecipientTerm(Message.RecipientType type, Address address)
  {
    super(address);
    this.type = type;
  }

  /**
   * Returns the recipient type.
   */
  public Message.RecipientType getRecipientType()
  {
    return type;
  }

  /**
   * Returns true only if the recipient address in the given message matches
   * the address specified in this term.
   */
  public boolean match(Message msg)
  {
    Address aaddress[];
    try
      {
        Address[] addresses = msg.getRecipients(type);
        if (addresses != null)
          {
            for (int i = 0; i < addresses.length; i++)
              {
                if (super.match(addresses[i]))
                  {
                    return true;
                  }
              }
          }
      }
    catch (Exception e)
      {
      }
    return false;
  }

  public boolean equals(Object other)
  {
    return (other instanceof RecipientTerm &&
           ((RecipientTerm) other).type.equals(type) &&
            super.equals(other));
  }

  public int hashCode()
  {
    return type.hashCode() + super.hashCode();
  }

}

