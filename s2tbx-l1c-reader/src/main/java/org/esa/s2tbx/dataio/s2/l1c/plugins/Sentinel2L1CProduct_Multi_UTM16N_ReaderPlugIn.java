
package org.esa.s2tbx.dataio.s2.l1c.plugins;

import org.esa.s2tbx.dataio.s2.l1c.Sentinel2L1CProductReaderPlugIn;

/**
 * Reader plugin for S2 MSI L1C over WGS84 / UTM Zone 16 N
 */
public class Sentinel2L1CProduct_Multi_UTM16N_ReaderPlugIn extends Sentinel2L1CProductReaderPlugIn {

    @Override
    public String getEPSG()
    {
        return "EPSG:32616";
    }

}