/* 
 * Copyright (C) 2014 Peter Cai
 *
 * This file is part of BlackLight
 *
 * BlackLight is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BlackLight is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BlackLight.  If not, see <http://www.gnu.org/licenses/>.
 */

package us.shandian.blacklight.ui.search;

import us.shandian.blacklight.api.search.SearchApi;
import us.shandian.blacklight.model.UserListModel;
import us.shandian.blacklight.support.LogF;
import us.shandian.blacklight.ui.friendships.FriendsFragment;

import static us.shandian.blacklight.BuildConfig.DEBUG;

public class SearchUserFragment extends FriendsFragment implements SearchFragment.Searcher
{
	private static final String TAG = SearchUserFragment.class.getSimpleName();
	
	private String mSearch;
	private int mPage = 0;
	
	public SearchUserFragment() {
		mNeedHeader = false;
	}
	
	@Override
	public void search(String q) {
		mSearch = q;
		onRefresh();
	}

	@Override
	protected void doRefresh(boolean param) {
		if (DEBUG) {
			LogF.d(TAG, "refreshing, isDown = %s", param ? "true" : "false");
		}
		
		if (param) {
			mPage = 0;
			mUsers.getList().clear();
		}
		
		UserListModel user = SearchApi.searchUser(mSearch, 50, ++mPage);
		
		mUsers.addAll(false, user);
	}

}
