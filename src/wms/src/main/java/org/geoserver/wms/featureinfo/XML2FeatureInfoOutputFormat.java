/* Copyright (c) 2014 OpenPlans - www.openplans.org. All rights reserved.
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.geoserver.wms.featureinfo;

import net.opengis.wfs.FeatureCollectionType;
import org.geoserver.platform.ServiceException;
import org.geoserver.wms.GetFeatureInfoRequest;
import org.geoserver.wms.WMS;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A GetFeatureInfo response handler specialized in producing GML 2 data for a GetFeatureInfo request.
 *
 * <p>
 *     This class is an alternative to <code>GML2FeatureInfoOutputFormat</code>.
 * </p>
 *
 * FIXME We cannot inherit from GML2FeatureInfoOutputFormat because we cannot access the &lt;init&gt;(WMS,String).
 *
 * @see GML2FeatureInfoOutputFormat
 * @author Alex van den Hoogen (Geodan)
 */
public class XML2FeatureInfoOutputFormat extends GetFeatureInfoOutputFormat {

    /**
     * The MIME type of the format this response produces: <code>"text/xml"</code>. This is
     * an alternative format for GML2: <code>"application/vnd.ogc.gml"</code>.
     */
    public static final String FORMAT = "text/xml";

    /**
     * This is in fact GML2, but inheritence doesn't seem to work. So we do it this way...
     */
    private GML2FeatureInfoOutputFormat gml2;

    /**
     * Default constructor, sets up the supported output format String.
     *
     * @param wms WMS to use.
     */
    public XML2FeatureInfoOutputFormat(final WMS wms) {
        super(FORMAT);
        this.gml2 = new GML2FeatureInfoOutputFormat(wms);
    }

    /**
     * Takes the <code>FeatureResult</code>s generated by the <code>execute</code> method in the
     * superclass and constructs a <code>GetFeaturesResult</code> which is passed to a
     * <code>GML2FeatureResponseDelegate</code>.
     *
     * @param results           Feature Collection type Results
     * @param request           Feature info Request
     * @param out               Outputstream to use
     * @throws org.geoserver.platform.ServiceException Exception
     * @throws java.io.IOException      Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void write(FeatureCollectionType results, GetFeatureInfoRequest request, OutputStream out) throws ServiceException, IOException {
        this.gml2.write(results, request, out);
    }
}
