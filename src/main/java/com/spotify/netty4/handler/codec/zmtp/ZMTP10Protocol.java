/*
* Copyright (c) 2012-2015 Spotify AB
*
* Licensed under the Apache License, Version 2.0 (the "License"); you may not
* use this file except in compliance with the License. You may obtain a copy of
* the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
* License for the specific language governing permissions and limitations under
* the License.
*/

package com.spotify.netty4.handler.codec.zmtp;

import static com.spotify.netty4.handler.codec.zmtp.ZMTPUtils.checkNotNull;
import static com.spotify.netty4.handler.codec.zmtp.ZMTPVersion.ZMTP10;

public class ZMTP10Protocol implements ZMTPProtocol {

  private final ZMTPConnectionType connectionType;

  private ZMTP10Protocol(final Builder builder) {
    this.connectionType = checkNotNull(builder.connectionType, "connectionType");
  }

  public ZMTPConnectionType connectionType() {
    return connectionType;
  }

  @Override
  public ZMTP10Handshaker handshaker(final ZMTPSession session) {
    return new ZMTP10Handshaker(session.localIdentity());
  }

  @Override
  public ZMTPVersion version() {
    return ZMTP10;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public boolean isEnveloped() {
    return connectionType.isEnveloped();
  }

  public static class Builder {

    private Builder() {
    }

    private ZMTPConnectionType connectionType;

    public Builder connectionType(final ZMTPConnectionType type) {
      this.connectionType = type;
      return this;
    }

    public ZMTP10Protocol build() {
      return new ZMTP10Protocol(this);
    }
  }
}
