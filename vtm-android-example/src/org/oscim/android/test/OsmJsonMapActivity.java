/*
 * Copyright 2014 Hannes Janetzek
 * Copyright 2016 devemux86
 *
 * This file is part of the OpenScienceMap project (http://www.opensciencemap.org).
 *
 * This program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.oscim.android.test;

import org.oscim.layers.TileGridLayer;
import org.oscim.layers.tile.bitmap.BitmapTileLayer;
import org.oscim.layers.tile.buildings.BuildingLayer;
import org.oscim.layers.tile.vector.VectorTileLayer;
import org.oscim.renderer.MapRenderer;
import org.oscim.theme.IRenderTheme;
import org.oscim.theme.ThemeLoader;
import org.oscim.theme.VtmThemes;
import org.oscim.tiling.source.geojson.HighroadJsonTileSource;
import org.oscim.tiling.source.geojson.OsmBuildingJsonTileSource;
import org.oscim.tiling.source.geojson.OsmLanduseJsonTileSource;
import org.oscim.tiling.source.geojson.OsmWaterJsonTileSource;
import org.oscim.tiling.source.geojson.RiverJsonTileSource;

import android.os.Bundle;

public class OsmJsonMapActivity extends MapActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mMap.setBaseMap(new BitmapTileLayer(mMap, new OsmLanduseJsonTileSource()));

		IRenderTheme theme = ThemeLoader.load(VtmThemes.OSMARENDER);
		MapRenderer.setBackgroundColor(theme.getMapBackground());

		VectorTileLayer l;
		l = new VectorTileLayer(mMap, new OsmWaterJsonTileSource());
		l.setRenderTheme(theme);
		l.tileRenderer().setOverdrawColor(0);
		mMap.layers().add(l);

		l = new VectorTileLayer(mMap, new RiverJsonTileSource());
		l.setRenderTheme(theme);
		l.tileRenderer().setOverdrawColor(0);
		mMap.layers().add(l);

		l = new VectorTileLayer(mMap, new HighroadJsonTileSource());
		l.setRenderTheme(theme);
		l.tileRenderer().setOverdrawColor(0);
		mMap.layers().add(l);

		l = new VectorTileLayer(mMap, new OsmBuildingJsonTileSource());
		l.setRenderTheme(theme);
		l.tileRenderer().setOverdrawColor(0);
		mMap.layers().add(l);
		mMap.layers().add(new BuildingLayer(mMap, l));

		mMap.layers().add(new TileGridLayer(mMap));

		mMap.setMapPosition(53.08, 8.83, Math.pow(2, 16));
	}
}
