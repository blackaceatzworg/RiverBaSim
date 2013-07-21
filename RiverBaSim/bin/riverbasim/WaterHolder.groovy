/**
 * 
 * This file was automatically generated by the Repast Simphony Agent Editor.
 * Please see http://repast.sourceforge.net/ for details.
 * 
 */

/**
 *
 * Set the package name.
 *
 */
package bin.riverbasim

/**
 *
 * Import the needed packages.
 *
 */
import java.io.*
import java.math.*
import java.util.*
import javax.measure.unit.*
import org.jscience.mathematics.number.*
import org.jscience.mathematics.vector.*
import org.jscience.physics.amount.*
import repast.simphony.adaptation.neural.*
import repast.simphony.adaptation.regression.*
import repast.simphony.context.*
import repast.simphony.context.space.continuous.*
import repast.simphony.context.space.gis.*
import repast.simphony.context.space.graph.*
import repast.simphony.context.space.grid.*
import repast.simphony.engine.environment.*
import repast.simphony.engine.schedule.*
import repast.simphony.engine.watcher.*
import repast.simphony.groovy.math.*
import repast.simphony.integration.*
import repast.simphony.matlab.link.*
import repast.simphony.query.*
import repast.simphony.query.space.continuous.*
import repast.simphony.query.space.gis.*
import repast.simphony.query.space.graph.*
import repast.simphony.query.space.grid.*
import repast.simphony.query.space.projection.*
import repast.simphony.parameter.*
import repast.simphony.random.*
import repast.simphony.space.continuous.*
import repast.simphony.space.gis.*
import repast.simphony.space.graph.*
import repast.simphony.space.grid.*
import repast.simphony.space.projection.*
import repast.simphony.ui.probe.*
import repast.simphony.util.*
import simphony.util.messages.*
import static java.lang.Math.*
import static repast.simphony.essentials.RepastEssentials.*

/**
 *
 * This is an agent.
 *
 */
public class WaterHolder  {

    /**
     *
     * Concentration of BOD
     * @field bodConcentration
     *
     */
    @Parameter (displayName = "BOD concentration (gr./m3)", converter = "riverbasim.WaterFeatureConverter", usageName = "bodConcentration")
    public riverbasim.WaterFeature getBodConcentration() {
        return bodConcentration
    }
    public void setBodConcentration(riverbasim.WaterFeature newValue) {
        bodConcentration = newValue
    }
    public riverbasim.WaterFeature bodConcentration = new riverbasim.WaterFeature(GetTickCount(), 0)

    /**
     *
     * Concentration of Nt
     * @field ntConcentration
     *
     */
    @Parameter (displayName = "Nitrogen Total concentration", converter = "riverbasim.WaterFeatureConverter", usageName = "ntConcentration")
    public riverbasim.WaterFeature getNtConcentration() {
        return ntConcentration
    }
    public void setNtConcentration(riverbasim.WaterFeature newValue) {
        ntConcentration = newValue
    }
    public riverbasim.WaterFeature ntConcentration = new riverbasim.WaterFeature(GetTickCount(), 0)

    /**
     *
     * Concentration of solids (MES)
     * @field solidConcentration
     *
     */
    @Parameter (displayName = "Solid concentration (gr/m3)", converter = "riverbasim.WaterFeatureConverter", usageName = "solidConcentration")
    public riverbasim.WaterFeature getSolidConcentration() {
        return solidConcentration
    }
    public void setSolidConcentration(riverbasim.WaterFeature newValue) {
        solidConcentration = newValue
    }
    public riverbasim.WaterFeature solidConcentration = new riverbasim.WaterFeature(GetTickCount(), 0)

    /**
     *
     * Concentration of Phosphorus Total
     * @field ptConcentration
     *
     */
    @Parameter (displayName = "Phosphorus Total concentration", converter = "riverbasim.WaterFeatureConverter", usageName = "ptConcentration")
    public riverbasim.WaterFeature getPtConcentration() {
        return ptConcentration
    }
    public void setPtConcentration(riverbasim.WaterFeature newValue) {
        ptConcentration = newValue
    }
    public riverbasim.WaterFeature ptConcentration = new riverbasim.WaterFeature(GetTickCount(), 0)

    /**
     *
     * Flow (amount) of water
     * @field amountWater
     *
     */
    @Parameter (displayName = "Amount of water (m3)", converter = "riverbasim.WaterFeatureConverter", usageName = "amountWater")
    public riverbasim.WaterFeature getAmountWater() {
        return amountWater
    }
    public void setAmountWater(riverbasim.WaterFeature newValue) {
        amountWater = newValue
    }
    public riverbasim.WaterFeature amountWater = new riverbasim.WaterFeature(GetTickCount(), 5000)

    /**
     *
     * Concentration of COD
     * @field codConcentration
     *
     */
    @Parameter (displayName = "COD concentration (gr./m3)", converter = "riverbasim.WaterFeatureConverter", usageName = "codConcentration")
    public riverbasim.WaterFeature getCodConcentration() {
        return codConcentration
    }
    public void setCodConcentration(riverbasim.WaterFeature newValue) {
        codConcentration = newValue
    }
    public riverbasim.WaterFeature codConcentration = new riverbasim.WaterFeature(GetTickCount(), 0)

    /**
     *
     * This value is used to automatically generate agent identifiers.
     * @field serialVersionUID
     *
     */
    private static final long serialVersionUID = 1L

    /**
     *
     * This value is used to automatically generate agent identifiers.
     * @field agentIDCounter
     *
     */
    protected static long agentIDCounter = 1

    /**
     *
     * This value is the agent's identifier.
     * @field agentID
     *
     */
    protected String agentID = "WaterHolder " + (agentIDCounter++)

    /**
     *
     * Mixing water
     * @method mixIncomingWater
     *
     */
    public def mixIncomingWater(double amountWaterReceived, double solidConcentrationReceived, double bodConcentrationReceived, double codConcentrationReceived, double ntConcentrationReceived, double ptConcentrationReceived) {

        // Define the return value variable.
        def returnValue

        // Note the simulation time.
        def time = GetTickCountInTimeUnits()

        // Mix water
        double solid = solidConcentration.get(GetTickCount());
        double bod=bodConcentration.get(GetTickCount());
        double cod=codConcentration.get(GetTickCount());
        double nt = ntConcentration.get(GetTickCount());
        double pt = ptConcentration.get(GetTickCount());
        double water = amountWater.get(GetTickCount());
        solidConcentration.put(GetTickCount(), (solid*water+solidConcentrationReceived*amountWaterReceived)/(water+amountWaterReceived));
        bodConcentration.put(GetTickCount(), (bod*water+solidConcentrationReceived*amountWaterReceived)/(water+amountWaterReceived));
        codConcentration.put(GetTickCount(), (cod*water+solidConcentrationReceived*amountWaterReceived)/(water+amountWaterReceived));
        ntConcentration.put(GetTickCount(), (nt*water+solidConcentrationReceived*amountWaterReceived)/(water+amountWaterReceived));
        ptConcentration.put(GetTickCount(), (pt*water+solidConcentrationReceived*amountWaterReceived)/(water+amountWaterReceived));
        amountWater.put(GetTickCount(), water+amountWaterReceived);
        // Return the results.
        return returnValue

    }

    /**
     *
     * This method provides a human-readable name for the agent.
     * @method toString
     *
     */
    @ProbeID()
    public String toString() {

        // Define the return value variable.
        def returnValue

        // Note the simulation time.
        def time = GetTickCountInTimeUnits()

        // Set the default agent identifier.
        returnValue = this.agentID
        // Return the results.
        return returnValue

    }


}

