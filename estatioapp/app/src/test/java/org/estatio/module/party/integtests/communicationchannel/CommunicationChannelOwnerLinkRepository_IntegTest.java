/*
 *
 *  Copyright 2012-2015 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.estatio.module.party.integtests.communicationchannel;

import java.util.List;

import javax.inject.Inject;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import org.incode.module.communications.dom.impl.commchannel.CommunicationChannelOwnerLink;
import org.incode.module.communications.dom.impl.commchannel.CommunicationChannelOwnerLinkRepository;
import org.incode.module.communications.dom.impl.commchannel.CommunicationChannelType;
import org.incode.module.communications.dom.impl.commchannel.PostalAddress;
import org.incode.module.communications.dom.impl.commchannel.PostalAddressRepository;
import org.incode.module.country.dom.impl.Country;
import org.incode.module.country.dom.impl.CountryRepository;

import org.estatio.module.country.fixtures.enums.Country_enum;
import org.estatio.module.party.dom.Party;
import org.estatio.module.party.dom.PartyRepository;
import org.estatio.module.party.fixtures.organisation.enums.Organisation_enum;
import org.estatio.module.party.fixtures.organisation.personas.OrganisationForTopModelGb;
import org.estatio.module.party.integtests.PartyModuleIntegTestAbstract;

public class CommunicationChannelOwnerLinkRepository_IntegTest extends PartyModuleIntegTestAbstract {

    @Before
    public void setupData() throws Exception {
        runFixtureScript(new FixtureScript() {
            @Override protected void execute(final ExecutionContext executionContext) {
                executionContext.executeChild(this, new OrganisationForTopModelGb());
            }
        });
    }

    public static class FindByCommunicationChannel extends CommunicationChannelOwnerLinkRepository_IntegTest {

        @Test
        public void happyCase() throws Exception {
            // given
            final Party party = partyRepository.findPartyByReference(Organisation_enum.TopModelGb.getRef());
            final Country country = Country_enum.GBR.findUsing(serviceRegistry);
            final PostalAddress postalAddress = postalAddressRepository.findByAddress(party, "1 Circle Square", "W2AXXX", "London", country);

            // when
            final CommunicationChannelOwnerLink communicationChannelOwnerLink = communicationChannelOwnerLinkRepository.findByCommunicationChannel(postalAddress);

            // then
            Assertions.assertThat(communicationChannelOwnerLink.getOwnerObjectType()).isEqualToIgnoringCase("org.estatio.dom.party.Organisation");
        }
    }

    public static class FindByOwner extends CommunicationChannelOwnerLinkRepository_IntegTest {

        @Test
        public void happyCase() throws Exception {
            // given
            final Party party = Organisation_enum.TopModelGb.findUsing(serviceRegistry);

            // when
            final List<CommunicationChannelOwnerLink> communicationChannelOwnerLinks = communicationChannelOwnerLinkRepository.findByOwner(party);

            // then
            Assertions.assertThat(communicationChannelOwnerLinks.size()).isEqualTo(5);
        }
    }

    public static class FindByOwnerAndCommunicationChannelType extends
            CommunicationChannelOwnerLinkRepository_IntegTest {

        @Test
        public void happyCase() throws Exception {
            // given
            final Party party = Organisation_enum.TopModelGb.findUsing(serviceRegistry);

            // when
            final List<CommunicationChannelOwnerLink> communicationChannelOwnerLinks = communicationChannelOwnerLinkRepository.findByOwnerAndCommunicationChannelType(party, CommunicationChannelType.PHONE_NUMBER);

            // then
            Assertions.assertThat(communicationChannelOwnerLinks.size()).isEqualTo(1);
            Assertions.assertThat(communicationChannelOwnerLinks.get(0).getCommunicationChannel().getType()).isEqualTo(CommunicationChannelType.PHONE_NUMBER);
        }
    }

    @Inject
    CommunicationChannelOwnerLinkRepository communicationChannelOwnerLinkRepository;

    @Inject
    PartyRepository partyRepository;

    @Inject
    CountryRepository countryRepository;

    @Inject
    PostalAddressRepository postalAddressRepository;
}
