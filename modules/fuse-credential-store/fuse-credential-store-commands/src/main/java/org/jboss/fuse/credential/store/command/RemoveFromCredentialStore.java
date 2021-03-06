/**
 *  Copyright 2005-2018 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.jboss.fuse.credential.store.command;

import java.util.Locale;

import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;

/**
 * Removes a value from the Credential store configured by the environment variables.
 */
@Command(scope = "credential-store", name = "remove", description = "Remove secret from the credential store")
@Service
public class RemoveFromCredentialStore extends AbstractCredentialStoreCommand {

    @Argument(index = 0, required = true, description = "Alias for credential Store entry (case insensitive)")
    private String alias;

    @Override
    public Object execute() throws Exception {
        if (!credentialStoreService.validate()) {
            return null;
        }

        alias = alias.toLowerCase(Locale.ROOT);

        if (!credentialStoreService.aliasExists(alias)) {
            System.out.println("No entry with alias \"" + alias + "\" found in credential store.");
            return null;
        }

        credentialStoreService.removeAlias(alias);

        return null;
    }

}
